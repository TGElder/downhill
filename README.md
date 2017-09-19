# downhill
This repository contains an implementation of an algorithm for randomly generating terrain that has a downhill path from any point to the edge of the map. This property is useful for generating realistic rivers. It is common for other terrain generation algorithms end up with 'bowls' in the landscape that make it difficult to add realistic rivers.   

## The Algorithm

The algorithm works by repeatedly dividing triangles into four small triangles.

We start with a simple mesh featuring 9 vertices, 16 edges and 8 triangles.

![3x3 Mesh](images/mesh3.png)

The shading shows the elevation of each point. The elevations are interpolated from the elevation of each vertex. The black lines show the edges of the triangles. This terrain is a pyramid, there is clearly a path from any point to the edge of the map.  

We now create new vertices at the midpoint of each edge and use them to create new triangles. The elevation of each new vertex must be between the elevation at each end of the edge. This ensures there is still a path from each vertex to the edge of the map.

![5x5 Mesh](images/mesh5.png)

If we repeat this process we start to get a nice random terrain that guarantees a route from each path to the edge.

![9x9 Mesh](images/mesh9.png)
![17x17 Mesh](images/mesh17.png)
![257x257 Mesh](images/mesh257.png)

This is however a bit boring - if you follow a path uphill from the edge of the map, you always get to the peak in the centre. Wouldn't it be better if there were sub-peaks?

## Implementation

The implementation is designed to minimise Java memory usage and garbage collection. 
1. The only part of the mesh held in memory are the x, y and z (elevation) coordinates of each point. We can work out where the edges and triangles are as the mesh has a predictable pattern.  
2. Where possible (and practical) a single copy of an object is mutated instead of creating a new object. In particular, the EdgeIterator and TriangleIterator that iterate over the edges or triangles of a mesh repeatedly return the same object with changed values. 