package com.tgelder.downhill.terrain;

import com.tgelder.downhill.image.AWTImage;
import com.tgelder.downhill.image.Image;
import com.tgelder.downhill.renderer.ContourRenderer;
import com.tgelder.downhill.renderer.FlowRenderer;
import com.tgelder.downhill.renderer.FlowSamplerRenderer;
import com.tgelder.downhill.renderer.HeightRenderer;
import com.tgelder.downhill.rngs.RNG;
import com.tgelder.downhill.rngs.RandomRNG;
import com.tgelder.downhill.writer.FlowWriter;
import com.tgelder.downhill.writer.HeightWriter;
import org.apache.commons.cli.*;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

  @SuppressWarnings("static-access")
  public static void main(String[] args) throws IOException, DownhillException, ParseException {

    List<Option> options = new ArrayList<>();

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
            .desc("Everything below this altitude will be coloured blue in the output image.")
            .hasArg()
            .argName("altitude")
            .build());

    options.add(
        Option.builder("maxAltitude")
            .desc("Altitude of highest point (altitude of lowest point is always zero).")
            .hasArg()
            .argName("altitude")
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
    } else {
      generateTerrain(
          Integer.parseInt(line.getOptionValue("seed", "0")),
          Integer.parseInt(line.getOptionValue("power", "10")),
          Double.parseDouble(line.getOptionValue("seaLevel", "0.25")),
          Double.parseDouble(line.getOptionValue("maxAltitude", "3000")),
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
    HeightRenderer zRenderer = new HeightRenderer();

    mesh = splitter.split(mesh, rng);
    mesh = splitter.split(mesh, rng);

    Image image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh.getZ(), Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);
    image.save("images/power4");

    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh.getZ(), Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);
    outlineCell(image, 8, 2, 4, Color.WHITE);
    image.save("images/oneCell");

    outlineCell(image, 8, 1, 4, Color.BLUE);
    outlineCell(image, 8, 2, 3, Color.BLUE);
    outlineCell(image, 8, 3, 4, Color.WHITE);
    outlineCell(image, 8, 2, 5, Color.WHITE);
    image.save("images/neighbours");

    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh.getZ(), Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);
    outlineCell(image, 4, 1, 2, Color.WHITE);
    image.save("images/parent");

    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh.getZ(), Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);
    outlineCell(image, 8, 2, 4, Color.WHITE);
    outlineCell(image, 4, 1, 1, Color.BLUE);
    outlineCell(image, 4, 0, 2, Color.BLUE);
    outlineCell(image, 4, 1, 2, Color.WHITE);
    image.save("images/neighboursParents");

    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh.getZ(), Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);
    outlineCell(image, 8, 2, 4, Color.WHITE);
    outlineCell(image, 8, 2, 5, Color.WHITE);
    outlineCell(image, 8, 3, 4, Color.WHITE);
    outlineCell(image, 8, 3, 5, Color.WHITE);
    image.save("images/grid");

    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh.getZ(), Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);
    outlineCell(image, 8, 2, 4, Color.ORANGE);
    outlineCell(image, 8, 2, 5, Color.RED);
    outlineCell(image, 8, 3, 4, Color.YELLOW);
    outlineCell(image, 8, 3, 5, Color.ORANGE);
    image.save("images/gridColoured");

    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh.getZ(), Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);
    outlineCell(image, 8, 2, 5, Color.RED);
    outlineCell(image, 8, 1, 5, Color.BLUE);
    outlineCell(image, 8, 2, 6, Color.BLUE);
    image.save("images/case1");

    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh.getZ(), Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);
    outlineCell(image, 8, 2, 4, Color.ORANGE);
    outlineCell(image, 8, 1, 4, Color.BLUE);
    image.save("images/case2a");

    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh.getZ(), Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);
    outlineCell(image, 8, 3, 5, Color.ORANGE);
    outlineCell(image, 8, 3, 6, Color.BLUE);
    image.save("images/case2b");

    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh.getZ(), Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);
    outlineCell(image, 8, 3, 4, Color.YELLOW);
    outlineCell(image, 8, 2, 4, Color.ORANGE);
    outlineCell(image, 8, 3, 5, Color.ORANGE);
    image.save("images/case3");

    mesh = splitter.split(mesh, rng);
    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh.getZ(), Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);
    image.save("images/power8");

    mesh = splitter.split(mesh, rng);
    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh.getZ(), Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);
    image.save("images/power16");

    mesh = splitter.split(mesh, rng);
    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh.getZ(), Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);
    image.save("images/power32");

    mesh = splitter.split(mesh, rng);
    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh.getZ(), Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);
    image.save("images/power64");

    mesh = splitter.split(mesh, rng);
    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh.getZ(), Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);
    image.save("images/power128");

    mesh = splitter.split(mesh, rng);
    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh.getZ(), Mesh.MIN_VALUE, Mesh.MAX_VALUE, image);
    image.save("images/power256");

    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh.getZ(), mesh.getMinZ(), mesh.getMaxZ(), image);
    image.save("images/power256full");

    image = new AWTImage(imageSize, imageSize);
    zRenderer.render(mesh.getZ(), mesh.getMinZ() * 0.8 + mesh.getMaxZ() * 0.2, mesh.getMaxZ(), image);
    image.save("images/power256sea");

    new FlowRenderer(256).render(FlowComputer.getFlow(mesh, DownhillComputer.getDownhill(mesh, Mesh.dx8, Mesh.dy8, rng), Mesh.dx8, Mesh.dy8), image);
    image.save("images/power256rivers");

    mesh = splitter.split(mesh, rng);

    image = new AWTImage(512, 512);
    zRenderer.render(mesh.getZ(), mesh.getMinZ() * 0.8 + mesh.getMaxZ() * 0.2, mesh.getMaxZ(), image);
    new FlowRenderer(512).render(FlowComputer.getFlow(mesh, DownhillComputer.getDownhill(mesh, Mesh.dx8, Mesh.dy8, rng), Mesh.dx8, Mesh.dy8), image);
    image.save("images/power512");


  }

  private static void outlineCell(Image image, int grid, int x, int y, Color color) {
    image.setColor(color);
    drawBox(image, (image.getWidth() * x) / grid, (image.getHeight() * y) / grid, image.getWidth() / grid);
  }

  private static void drawBox(Image image, int x, int y, int size) {
    image.drawLine(x, y, x + size - 1, y);
    image.drawLine(x + size - 1, y, x + size - 1, y + size - 1);
    image.drawLine(x + size - 1, y + size - 1, x, y + size - 1);
    image.drawLine(x, y + size - 1, x, y);
  }


  private static void generateTerrain(
      int seed,
      int power,
      double seaLevel,
      double maxAltitude,
      int rivers,
      String destination) throws IOException, DownhillException {

    Terrain terrain = new Terrain(seed, power, maxAltitude);

    HeightRenderer zRenderer = new HeightRenderer();

    int size = (int) (Math.pow(2, power));

    Image image = new AWTImage(size, size);
    zRenderer.render(terrain.getAltitudes(), seaLevel, maxAltitude, image);
    FlowRenderer flowRenderer = new FlowRenderer(rivers);
    flowRenderer.render(terrain.getFlow(), image);
    String fileName = String.format("%s/%s-%s", destination, seed, power);
    ContourRenderer contourRenderer = new ContourRenderer();
    Image countourImage = new AWTImage(size, size);
    System.out.println("Rendering contours");
    ContourRenderer.render(terrain.getAltitudes(), terrain.getMaxAltitude()/16.0, 0, terrain.getMaxAltitude(), countourImage);
    countourImage.save(String.format("%s_countours", fileName));
//    new FlowSamplerRenderer(16).render(terrain.getFlowSample(32), image);
    image.save(fileName);
    HeightWriter.write(terrain, String.format("%s", fileName));
    new FlowWriter(rivers).write(terrain, String.format("%s_rivers", fileName));

  }

}
