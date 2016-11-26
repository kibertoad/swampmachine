# Scripting

Swampmachine provides several scripting implementations;

## Usage

1) Create ScriptEntityFactory instance;
2) Get ScriptParser instance via ScriptEntityFactory.getParserInstance();
3) Parse scripts into SwampScripts via ScriptParser.parseScript() or ScriptParser.parseScriptsFromPath();
4) Prepare script execution context as a SwampBinding (created in a ScriptEntityFactory.getBindingInstance);
5) Pass prepared SwampBinding to SwampScript.invoke();
6) Process SwampScript.invoke() execution result that is returned as SwampScriptInvokationResult.

## Implementations

Performance comparison provided based on simple test executing on 20 threads (100 times on each thread). It not supposed to be extremely accurate, but seems to be pretty consistent
with insignificant deviations.

### Groovy

Moderately fast scripting engine.
0,155 sec.


### Jython

Fastest scripting engine.
0,060 secs


### JRuby

Slowest scripting engine