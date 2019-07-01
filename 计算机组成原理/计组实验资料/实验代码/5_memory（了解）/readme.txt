本实验共给出了4套参考设计,如下：

-memory_async ---data_ram  自行搭建的异步数据RAM
               |
               |-inst_rom  自行搭建的指令ROM

-memory_sync  ---data_ram  调用IP库实例化的同步数据RAM
               |
               |-inst_rom  调用IP库实例化的同步指令ROM
