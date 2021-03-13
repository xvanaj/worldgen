# How it works?
- renders generated world map to given stage
- to be able to scroll on map via mouse you should use ScrollingStage
- allows to determine which objects/terrain/artefacts should be rendered. 
List and its default values is following:
   -     protected boolean scalingShader = false;
         protected boolean drawPolitical;
         protected final boolean showCities = true;
         protected boolean dungeonNames, cityNames = true;
         protected boolean drawRoads = true;
         protected boolean drawTrees = true;
         protected boolean drawGrid = false;
         protected boolean dayNightCycle = true;
         protected boolean lineOfSight = false;
         protected boolean drawObjects = true;
         protected boolean drawBiomeRegions;
         protected boolean drawCollisions;
         protected final boolean redrawPolitical = true;
         protected boolean drawTerrain = true;
         protected boolean drawWater = true;
         protected final boolean drawHeightType = false; 
- allows orthogonal or isometric projection
- allows also isometric projection with slopes but that requires also height levels calculated. Ie needs some twea
### Sprite batch renderer
- no optimization. Simply renders all tiles with each frame.
- 60 fps up to 512x512 maps
### Sprite cache renderer
- creates sprite caches with creation of renderer which significantly speeds up rendering
- max cache size is cca 8000 tiles so list of caches is needed for bigger maps 
- 60 fps for up to 2048 * 2048 maps 
- problem is that cache has to be recreated when map is changed. 

### Scrolling stage
- allows scrolling by mouse
- bounds can be fixed so there is no "black area" rendered
- or can allow scrolling to the other side of world for "wrapped" worlds. Ie north to south, west to east. 