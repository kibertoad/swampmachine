# GUI components

## SelectableToggleableElementList

Example usage: trait list where you can both select trait to see its description and select/delect it for your character

### Usage

1) Implement abstract method "getElementList";
2) Add observers that will process its Observable "selectedElement" changes;
3) Bind "buttonSource" to the view;
4) Invoke "fillButtonList" whenever displayed list should be updated.