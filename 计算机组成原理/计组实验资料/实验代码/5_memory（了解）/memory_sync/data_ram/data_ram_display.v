`timescale 1ns / 1ps
//*************************************************************************
//   > 文件名: data_ram_display.v
//   > 描述  ：数据存储器模块显示模块，调用FPGA板上的IO接口和触摸屏
//   > 作者  : LOONGSON
//   > 日期  : 2016-04-14
//*************************************************************************
module data_ram_display(
    //时钟与复位信号
    input clk,
    input resetn,    //后缀"n"代表低电平有效

    //拨码开关，用于产生写使能和选择输入数
    input [3:0] wen,
    input [1:0] input_sel,

    //led灯，用于指示写使能信号，和正在输入什么数据
    output [3:0] led_wen,
    output led_addr,      //指示输入读写地址
    output led_wdata,     //指示输入写数据
    output led_test_addr, //指示输入test地址

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
//-----{LED显示}begin
    assign led_wen       = wen;
    assign led_addr      = (input_sel==2'd0);
    assign led_wdata     = (input_sel==2'd1);
    assign led_test_addr = (input_sel==2'd2);
//-----{LED显示}end
//-----{调用数据储存器模块}begin
    //数据存储器多增加一个读端口，用于读出特定内存地址显示在触摸屏上
    reg  [31:0] addr;
    reg  [31:0] wdata;
    wire [31:0] rdata;
    reg  [31:0] test_addr;
    wire [31:0] test_data;  

    data_ram data_ram_module(
        .clka  (clk           ),
        .wea   (wen           ),
        .addra (addr[9:2]     ),
        .dina  (wdata         ),
        .douta (rdata         ),
        .clkb  (clk           ),
        .web   (4'd0          ),
        .addrb (test_addr[9:2]),
        .doutb (test_data     ),
        .dinb  (32'd0         )
    );
//-----{调用寄存器堆模块}end

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
    //当input_sel为2'b00时，表示输入数为读写地址，即addr
    always @(posedge clk)
    begin
        if (!resetn)
        begin
            addr <= 32'd0;
        end
        else if (input_valid &&  input_sel==2'd0)
        begin
            addr[31:2] <= input_value[31:2];
        end
    end
    
    //当input_sel为2'b01时，表示输入数为写数据，即wdata
    always @(posedge clk)
    begin
        if (!resetn)
        begin
            wdata <= 32'd0;
        end
        else if (input_valid && input_sel==2'd1)
        begin
            wdata <= input_value;
        end
    end
    
    //当input_sel为2'b10时，表示输入数为test地址，即test_addr
    always @(posedge clk)
    begin
        if (!resetn)
        begin
            test_addr  <= 32'd0;
        end
        else if (input_valid && input_sel==2'd2)
        begin
            test_addr[31:2] <= input_value[31:2];
        end
    end
//-----{从触摸屏获取输入}end

//-----{输出到触摸屏显示}begin
//根据需要显示的数修改此小节，
//触摸屏上共有44块显示区域，可显示44组32位数据
//44块显示区域从1开始编号，编号为1~44，
    always @(posedge clk)
    begin
       case(display_number)
           6'd1:
           begin
               display_valid <= 1'b1;
               display_name  <= "ADDR ";
               display_value <= addr;
           end
           6'd2: 
           begin
               display_valid <= 1'b1;
               display_name  <= "WDATA";
               display_value <= wdata;
           end
           6'd3: 
           begin
               display_valid <= 1'b1;
               display_name  <= "RDATA";
               display_value <= rdata;
           end
           6'd5: 
           begin
               display_valid <= 1'b1;
               display_name  <= "T_ADD";
               display_value <= test_addr;
           end
           6'd6: 
           begin
               display_valid <= 1'b1;
               display_name  <= "T_DAT";
               display_value <= test_data;
           end
           default :
           begin
               display_valid <= 1'b0;
               display_name  <= 40'd0;
               display_value <= 32'd0;
           end
       endcase
    end
//-----{输出到触摸屏显示}end
//----------------------{调用触摸屏模块}end---------------------//
endmodule
