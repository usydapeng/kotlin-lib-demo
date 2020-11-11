# 如何使用kotlin写lib库

kotlin代码中如果使用了不能为空的语法，则需要导入`kotlin-stdlib`库，否则会报错

```
Exception in thread "main" java.lang.NoClassDefFoundError: kotlin/jvm/internal/Intrinsics
```

建议使用允许为空的语法,如: `String?`

> 参考
>
> - [理解Kotlin中的 @JvmOverloads、@JvmStatic、@JvmName、@JvmField注解](https://www.jianshu.com/p/0d312fac3a65)
> - [JvmName 注解在 Kotlin 中的应用](https://droidyue.com/blog/2019/09/01/jvm-name-annotations-kotlin/)
