
## Getting Started

1. 运行环境

   * Java 版本 ：JDK 17 或更高版本。
2. 编译与运行
   1. 编译

      ```bash
      ./build.bat
      ```
   2. 运行

      ```bash
      ./run.bat
      ```
   3. 测试

      ```bash
      ./testcase.bat
      ```
### 使用说明

程序运行时，会显示以下字符串：

```
Input an infix expression and output its postfix notation:
```

根据提示输入操作中缀表达式，进行转换。

### Folder structure

本项目文件结构如下所示：

```
LAB_0/
    ├─bin                   # 编译后的 class 文件
    ├─lib
    ├─src                   # 源代码
    │  ├─doc                # javadoc
    │  └─lab1
    │      └─ Postfix.java  # 中缀表达式转换器
    ├─build.bat             # Windows 编译脚本
    ├─run.bat               # Windows 运行脚本
    ├─testcase.bat          # Windows 测试脚本
    └─testcases             # 测试样例
        ├─ tc-001.infix
        ├─ tc-001.postfix
        ├─ tc-002.infix
        ├─ tc-002.postfix
        ├─ tc-003.infix
        ├─ tc-003.postfix
        ├─ tc-004.infix
        ├─ tc-004.postfix
        ├─ tc-005.infix
        └─ tc-005.postfix
```
