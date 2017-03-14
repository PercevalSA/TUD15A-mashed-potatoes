# TUD15A mashed potatoes
A simple snake developed during 2017 March Athens program session

## Quick Start

1. Clone repo `git clone https://github.com/PercevalSA/TUD15A-mashed-potatoes.git`
2. Open the project in IntelliJ IDEA
3. Click on "Run > Run..."
4. Click on "0. Edit Configuration..."
5. On "VM Options" put the following : `-Djava.library.path=<lwjgl-X.X path>/native/<linux|macosx|solaris|windows>`
    * In my case, I have : `-Djava.library.path=./lib/lwjgl-2.9.3/native/macosx`


## Answer To Questions

### First Class

1. Write a natural language description of why and how the pattern is implemented in your code.

We implemented the sigleton patern in the `Application`, `GameController` and `GameViewer`.
We don't user multi thread so we don't need `synchronise` constructors so all those three singleton look like :

```java
private static Class instance;

public static Class getInstance() {
    if(instance == null) {
        instance = new Game();
    }
    return instance;
}
```

There is only one game running at the time. So there is only one application so only one controller.
Moreover there is only one window so one viewer.

2. Make a class diagram of how the pattern is structured statically in your code.


3. Make a sequence diagram of how the pattern works dynamically in your code.

