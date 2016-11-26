# Swampmachine

Swampmachine is a high-level rapid game development framework based on LibGDX and Spring frameworks.

> The overriding design goal for Swampmachine is to make game development as fast as possible. 


Main features
  - Scripting API. Implementations: Groovy, Jython. (JRuby support currently in alpha state);
  - Templating API. Implementations: Mustache, Pebble;
  - Persistence wrappers: Hibernate, Mybatis, Liquibase;
  - Tiled map support with pathfinding and dynamic entity placement;
  - Spritesheet generation util ("Preflight");
  - Compression API. Supported implementations (compression): BZip2, 7Z, Snappy. Archival: Tar;
  - Entity management framework;
  - Publisher/subscriber event framework for variable state tracking;
  - Asset loading framework;
  - JSON-based GUI layout composition framework Mahler;
  - Sound and music playback (based on LWJGL OpenAL implementation);
  - Enriched scene2d.ui widgets.

### Tech

Swampmachine relies upon a number of open source projects:

* [libGDX] - cross-platform Java game development framework.
* [Spring] - Spring framework


   [libGDX]: <https://libgdx.badlogicgames.com/>
   [spring]: <https://projects.spring.io/spring-framework/>