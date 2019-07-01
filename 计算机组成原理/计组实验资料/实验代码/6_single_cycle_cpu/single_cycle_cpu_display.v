`timescale 1ns / 1ps
//*************************************************************************
//   > 文件名: single_cycle_cpu_display.v
//   > 描述  ：单周期CPU显示模块，调用FPGA板上的IO接口和触摸屏
//   > 作者  : LOONGSON
//   > 日期  : 2016-04-14
//*************************************************************************
module single_cycle_cpu_display(
    //时钟与复位信号
    input clk,
    input resetn,    //后缀"n"代表低电平有效

    //脉冲开关，用于产生脉冲clk，实现单步执行
    input btn_clk,

    //触摸屏相关接口，不需要更改
    output lcd_rst,
    output lcd_cs,
    output lcd_rs,
    output lcd_wr,
    output lcd_rd,
    inout[15:0] lcd_data_io,
    output lcd_bl_ctr,
    inout ct_int,
    inout ct_sda,
    output ct_scl,
    output ct_rstn
    );
//-----{时钟和复位信号}begin
//不需要更改，用于单步调试
    wire cpu_clk;    //单周期CPU里使用脉冲开关作为时钟，以实现单步执行
	 reg btn_clk_r1;
	 reg btn_clk_r2;
    always @(posedge clk)
    begin
        if (!resetn)
        begin
            btn_clk_r1<= 1'b0;
        end
        else
        begin
            btn_clk_r1 <= ~btn_clk;
        end

        btn_clk_r2 <= btn_clk_r1;
    end
	 
	 wire clk_en;
    assign clk_en = !resetn || (!btn_clk_r1 && btn_clk_r2);
    BUFGCE cpu_clk_cg(.I(clk),.CE(clk_en),.O(cpu_clk));
//-----{时钟和复位信号}end

//-----{调用单周期CPU模块}begin

    //用于在FPGA板上显示结果
    wire [31:0] cpu_pc;    //CPU的PC
    wire [31:0] cpu_inst;  //该PC取出的指令
    wire [ 4:0] rf_addr;   //扫描寄存器堆的地址
    wire [31:0] rf_data;   //寄存器堆从调试端口读出的数据
    reg  [31:0] mem_addr;  //要观察的内存地址
    wire [31:0] mem_data;  //内存地址对应的数据
    single_cycle_cpu cpu(
        .clk     (cpu_clk   ),
        .resetn  (resetn    ),

        .rf_addr (rf_addr ),
        .mem_addr(mem_addr),
        .rf_data (rf_data ),
        .mem_data(mem_data),
        .cpu_pc  (cpu_pc  ),
        .cpu_inst(cpu_inst)
    );
//-----{调用单周期CPU模块}end

//---------------------{调用触摸屏模块}begin--------------------//
//-----{实例化触摸屏}begin
//此小节不需要更改
    reg         display_valid;
    reg  [39:0] display_name;
    reg  [31:0] display_value;
    wire [5 :0] display_number;
    wire        input_valid;
    wire [31:0] input_value;

    lcd_module lcd_module(
        .clk            (clk           ),   //10Mhz
        .resetn         (resetn        ),

        //调用触摸屏的接口
        .display_valid  (display_valid ),
        .display_name   (display_name  ),
        .display_value  (display_value ),
        .display_number (display_number),
        .input_valid    (input_valid   ),
        .input_value    (input_value   ),

        //lcd触摸屏相关接口，不需要更改
        .lcd_rst        (lcd_rst       ),
        .lcd_cs         (lcd_cs        ),
        .lcd_rs         (lcd_rs        ),
        .lcd_wr         (lcd_wr        ),
        .lcd_rd         (lcd_rd        ),
        .lcd_data_io    (lcd_data_io   ),
        .lcd_bl_ctr     (lcd_bl_ctr    ),
        .ct_int         (ct_int        ),
        .ct_sda         (ct_sda        ),
        .ct_scl         (ct_scl        ),
        .ct_rstn        (ct_rstn       )
    ); 
//-----{实例化触摸屏}end

//-----{从触摸屏获取输入}begin
//根据实际需要输入的数修改此小节，
//建议对每一个数的输入，编写单独一个always块
    always @(posedge clk)
    begin
        if (!resetn)
        begin
            mem_addr <= 32'd0;
        end
        else if (input_valid)
        begin
            mem_addr <= input_value;
        end
    end
    assign rf_addr = display_number-6'd5;
//-----{从触摸屏获取输入}end

//-----{输出到触摸屏显示}begin
//根据需要显示的数修改此小节，
//触摸屏上共有44块显示区域，可显示44组32位数据
//44块显示区域从1开始编号，编号为1~44，
    always @(posedge clk)
    begin
        if (display_number >6'd4 && display_number <6'd37 )
        begin  //块号5~36显示32个通用寄存器的值
            display_valid <= 1'b1;
            display_name[39:16] <= "REG";
            display_name[15: 8] <= {4'b0011,3'b000,rf_addr[4]};
            display_name[7 : 0] <= {4'b0011,rf_addr[3:0]}; 
            display_value       <= rf_data;
          end
        else
        begin
            case(display_number)
                6'd1 : //显示PC值
                begin
                    display_valid <= 1'b1;
                    display_name  <= "   PC";
                    display_value <= cpu_pc;
                end
                6'd2 : //显示PC取出的指令
                begin
                    display_valid <= 1'b1;
                    display_name  <= " INST";
                    display_value <= cpu_inst;
                end
                6'd3 : //显示要观察的内存地址
                begin
                    display_valid <= 1'b1;
                    display_name  <= "MADDR";
                    display_value <= mem_addr;
                end
                6'd4 : //显示该内存地址对应的数据
                begin
                    display_valid <= 1'b1;
                    display_name  <= "MDATA";
                    display_value <= mem_data;
                end
                default :
                begin
                    display_valid <= 1'b0;
                end
            endcase
        end
    end
//-----{输出到触摸屏显示}end
//----------------------{调用触摸屏模块}end---------------------//
endmodule
