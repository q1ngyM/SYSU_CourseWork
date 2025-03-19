## Getting Started

1. 运行环境

   * Java 版本 ：JDK 17 或更高版本。
2. 编译与运行
   1. 运行
      注：compile.bat 在 run.bat中调用，可以不提前编译。 测试同理。

      ```bash
      ./run.bat
      ``` 
    2. 测试  
        ```bash
        ./test.bat
        ``` 
### 使用说明

程序启动后，会显示以下菜单：
```
==== 个人所得税计算器 ====
1. 计算个人所得税
2. 修改起征点
3. 修改税率
4. 查看当前税率表
5. 重置默认税率表
6. 退出
请输入操作:
```
根据提示输入操作编号，按回车键执行相应功能。

### Folder structure

本项目文件结构如下所示：

```
LAB_0/
├── bin/                  # 编译后的 class 文件
├── lib/                  # 第三方库（如 JUnit）
├── src/                  # 源代码
│   └── TaxCalculatorSystem/
│       ├── TaxCLI.java   # 命令行用户界面
│       ├── TaxConfig.java # 配置管理模块
│       └── TaxCalculator.java # 计算模块
├── test/                 # 测试代码
│   └── TaxCalculatorSystem/
│       ├── TaxCalculatorTest.java # 单元测试模块
│       └── TaxCLITest.java # 命令行测试模块
├── README.md             # 项目说明文档
├── design.pdf            # 项目设计文档
├── test.bat              # Windows 测试脚本
└── run.bat               # Windows 运行脚本
```

