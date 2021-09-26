# codeless-automation

* Codeless testing framework helps the developers to run the automation test cases without writing any code.
* Developers need to provide .excel sheet to the codeless testing framework and it executes all the test cases row by row from the .excel sheet.


To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency

```
dependencies {
    implementation 'com.github.sandycommits:codeless-automation:1.0'
}
```
