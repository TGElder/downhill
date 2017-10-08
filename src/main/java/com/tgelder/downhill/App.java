package com.tgelder.downhill;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.tgelder.downhill.image.AWTImage;
import com.tgelder.downhill.image.Image;
import com.tgelder.downhill.mesh.DownhillComputer;
import com.tgelder.downhill.mesh.DownhillException;
import com.tgelder.downhill.mesh.FlowComputer;
import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.MeshSplitter;
import com.tgelder.downhill.renderer.FlowRenderer;
import com.tgelder.downhill.renderer.ZRenderer;
import com.tgelder.downhill.rngs.RNG;
import com.tgelder.downhill.rngs.RandomRNG;

public class App {
  
  @SuppressWarnings("static-access")
  public static void main(String [] args) throws IOException, DownhillException, ParseException {
        
    List<Option> options = new ArrayList<> ();

    options.add(
        Option.builder("seed")
        .desc("Random seed.")
        .hasArg()
        .argName("seed")
        .build());
    
    options.add(
        Option.builder("power")
        .desc("Output terrain will have width 2^power.")
        .hasArg()
        .argName("power")
        .build());
    
    options.add(
        Option.builder("seaLevel")
        .desc("Everything below this level will be coloured blue in the output image."
            + "Expressed as ratio where 0 = nothing blue and 1 = everything blue.")
        .hasArg()
        .argName("threshold")
        .build());
    
    options.add(
        Option.builder("rivers")
        .desc("Every cell with flow over this threshold will be coloured blue in the output image.")
        .hasArg()
        .argName("threshold")
        .build());
    
    options.add(
        Option.builder("destination")
        .desc("Destination for output.")
        .hasArg()
        .argName("path")
        .build());
    
    
    Options commandOptions = new Options();
    Options helpOptions = new Options();
    
    for (Option option : options) {
      commandOptions.addOption(option);
      helpOptions.addOption(option);
    }
    
    commandOptions.addOption("generateImagesForReadMe", false, ""); // hidden option, not in helpOptions
    
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("downhill", helpOptions);
    
    CommandLineParser parser = new DefaultParser();
    CommandLine line = parser.parse(commandOptions, args);

    if (line.hasOption("generateImagesForReadMe")) {
      generateImagesForReadMe();
    }
    else {
      generateTerrain(
          Integer.parseInt(line.getOptionValue("seed", "0")),
          Integer.parseInt(line.getOptionValue("power", "10")),
          Double.parseDouble(line.getOptionValue("seaLevel", "0.25")),
          Integer.parseInt(line.getOptionValue("rivers", "2000")),
          line.getOptionValue("destination", ""));
     
    }
     
  }
  
  private static void generateImagesForReadMe() throws IOException, DownhillException {
    
    int imageSize = 256;
    
    Mesh mesh = new Mesh(1);
    mesh.setZ(Mesh.MAX_VALUE);

    MeshSplitter splitter = new MeshSplitter(0.05, 0.95);
    RNG rng = new RandomRNG(19);
    ZRenderer zRenderer = new ZRenderer();

    mesh = splitter.split(mesh, rng);
    mesh = splitter.split(mesh, rng);
    
    Image image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh, Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);
    image.save("images/power4");
    image.setColor(Color.RED);
    
    for (int x = 4; x < 6; x++) {
      for (int y = 2; y < 4; y++) {
        drawBox(image, (imageSize * x) / 8, (imageSize * y) / 8, imageSize / 8);
      }
    }
    
    image.save("images/power4boxes1");
    
    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh, Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);    
    image.setColor(Color.RED);
    drawBox(image, (imageSize * 4) / 8, (imageSize * 2) / 8, imageSize / 8);
    image.save("images/power4boxes2");
    
    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh, Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);    
    image.setColor(Color.RED);
    drawBox(image, (imageSize * 4) / 8, (imageSize * 2) / 8, imageSize / 8);
    drawBox(image, (imageSize * 4) / 8, (imageSize * 0) / 8, imageSize / 4);
    drawBox(image, (imageSize * 2) / 8, (imageSize * 2) / 8, imageSize / 4);
    image.save("images/power4boxes3");
    
    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh, Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);    
    image.setColor(Color.RED);
    drawBox(image, (imageSize * 4) / 8, (imageSize * 2) / 8, imageSize / 8);
    drawBox(image, (imageSize * 4) / 8, (imageSize * 1) / 8, imageSize / 8);
    drawBox(image, (imageSize * 3) / 8, (imageSize * 2) / 8, imageSize / 8);
    image.save("images/power4boxes4");
    
    mesh = splitter.split(mesh, rng);
    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh, Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);    
    image.save("images/power8");

    mesh = splitter.split(mesh, rng);
    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh, Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);    
    image.save("images/power16");
    
    mesh = splitter.split(mesh, rng);
    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh, Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);    
    image.save("images/power32");
    
    mesh = splitter.split(mesh, rng);
    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh, Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);    
    image.save("images/power64");
    
    mesh = splitter.split(mesh, rng);
    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh, Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);    
    image.save("images/power128");
    
    mesh = splitter.split(mesh, rng);
    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh, Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);    
    image.save("images/power256");
    
    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh, mesh.getMinZ(), mesh.getMaxZ(), image);    
    image.save("images/power256full");

    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh, mesh.getMinZ() * 0.8 + mesh.getMaxZ() * 0.2, mesh.getMaxZ(), image);    
    image.save("images/power256sea");
        
    new FlowRenderer(256).render(FlowComputer.getFlow(mesh, DownhillComputer.getDownhill(mesh)), image);
    image.save("images/power256rivers");
    
    mesh = splitter.split(mesh, rng);
    
    image = new AWTImage(512, 512);
    zRenderer.render(mesh, mesh.getMinZ() * 0.8 + mesh.getMaxZ() * 0.2, mesh.getMaxZ(), image);
    new FlowRenderer(512).render(FlowComputer.getFlow(mesh, DownhillComputer.getDownhill(mesh)), image);
    image.save("images/power512");

    
  }
  
  private static void drawBox(Image image, int x, int y, int size) {
    image.drawLine(x, y, x + size, y);
    image.drawLine(x + size, y, x + size, y + size);
    image.drawLine(x + size, y + size, x, y + size);
    image.drawLine(x, y + size, x, y);
  }
  
  private static void generateTerrain(
      int seed,  
      int power, 
      double seaLevel, 
      int rivers, 
      String destination) throws IOException, DownhillException {
    
    Mesh mesh = new Mesh(1);
    mesh.setZ(Mesh.MAX_VALUE);

    ZRenderer zRenderer = new ZRenderer();
    
    MeshSplitter splitter = new MeshSplitter(0.05, 0.95);
    RNG rng = new RandomRNG(seed);
    
    int size = (int)(Math.pow(2, power));
    
    for (int i=0; i<power; i++) {
      mesh = splitter.split(mesh, rng);
    }
    
    double zRendererSeaLevel = Mesh.MIN_VALUE * (1 - seaLevel) + Mesh.MAX_VALUE * seaLevel;
    
    Image image = new AWTImage(size, size);
    zRenderer.render(mesh, zRendererSeaLevel, mesh.getMaxZ(), image);
    FlowRenderer flowRenderer = new FlowRenderer(rivers);
    flowRenderer.render(FlowComputer.getFlow(mesh, DownhillComputer.getDownhill(mesh)), image);
    image.save(String.format("%sseed%s_power%s_seaLevel%s_rivers%s", 
        destination, seed, power, seaLevel, rivers));
  }

}
