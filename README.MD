kotlin代码中如果使用了不能为空的语法，则需要导入`kotlin-stdlib`库，否则会报错

```
Exception in thread "main" java.lang.NoClassDefFoundError: kotlin/jvm/internal/Intrinsics
```

建议使用允许为空的语法,如: `String?`
