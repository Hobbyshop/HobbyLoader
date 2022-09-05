## Hobby Loader
Don't take this project to serious, I just make it to learn about jar-loading and reflections and all that.
In addition to that I don't think you will ever be able to create a mod with this loader :/

### Usage
In the root of your project you need to have a file called `module.json`.
This file contains information about your mod, such as it's name.

Here's an example:
```json
{
    "name": "Example",
    "description": "Just an example",
    "author": "Hobbyshop",
    "entry": "com.example.mod.Main"
}
```
Only the fields `"name"` and `"entry"` are required.

### Main class
The main class needs to extend the ``HobbyMod`` class.

Here is an example for that.

```java
package me.hobbyshop.example;

import me.hobbyshop.hobbyloader.api.mod.HobbyMod;
import me.hobbyshop.hobbyloader.api.mod.ModInfo;

public class Main extends HobbyMod {

    public Main(ModInfo info) {
        super(info);
    }

    // This will be called once the mod was initialized on the start
    @Override
    public void onInitialize() {

    }

    // This will be called when the mod got disabled while shutting down the game
    @Override
    public void onKill() {

    }
}

```
