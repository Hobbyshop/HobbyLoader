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
