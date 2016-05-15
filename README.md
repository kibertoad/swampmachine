# swampmachine
Swampmachine is a high-level rapid game development framework, based on libGDX (https://libgdx.badlogicgames.com/) and Spring (https://projects.spring.io/spring-framework/) frameworks. 
It aims to provide a set of easily reusable and extendable functionality for games of different genres, covering state management,
asset loading, entity management, loading and using Tiled (http://www.mapeditor.org/) maps, persistence and scripting (using Groovy or Jython).

In order to understand the workflow and a lifecycle of a Swampmachine game (which does follow the basic structure of a libGDX application, but makes an extensive use of Spring and annotations to bind everything together), see this diagram: https://github.com/kibertoad/swampmachine/blob/master/doc/Game%20lifecycle.png

At this point **swampmachine-core** and **swampmachine-mvc** are more or less fleshed out and ready for usage. Rest of the framework is still very much a work in progress.

This is my third attempt at programming a game framework. Earlier attempts could only be interesting for historical purposes (I was an 
absolutely awful self-taught programmer back then), but I'm still including links to them for completeness sake:

https://bitbucket.org/kibertoad/transientlibs (first attempt)

https://bitbucket.org/kibertoad/pyramide9 (second attempt)
