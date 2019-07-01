//*************************************************************************
//   > 文件名: multiply_display.v
//   > 描述  ：乘法器显示模块，调用FPGA板上的IO接口和触摸屏
//   > 作者  : LOONGSON
//   > 日期  : 2016-04-14
//*************************************************************************
module multiply_display(
    //时钟与复位信号
    input clk,
    input resetn,    //后缀"n"代表低电平有效

    //拨码开关，用于选择输入数
    input input_sel, //0:输入为乘数1;1:输入为乘数2
    input sw_begin,
    
    //乘法结束信号
    output led_end,

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
//-----{调用乘法器模块}begin
    wire        mult_begin;
    reg  [31:0] mult_op1; 
    reg  [31:0] mult_op2;  
    wire [63:0] product; 
    wire        mult_end;  
    assign mult_begin = sw_begin;
    assign led_end = mult_end;
    multiply multiply_module (
        .clk       (clk       ),
        .mult_begin(mult_begin),
        .mult_op1  (mult_op1  ), 
        .mult_op2  (mult_op2  ),
        .product   (product   ),
        .mult_end  (mult_end  )
    );
    reg [63:0] product_r;
    always @(posedge clk)
    begin
        if (!resetn)
        begin
            product_r <= 64'd0;
        end
        else if (mult_end)
        begin
            product_r <= product;
        end
    end
//-----{调用乘法器模块}end

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
    //当input_sel为0时，表示输入数为乘数1
    always @(posedge clk)
    begin
        if (!resetn)
        begin
            mult_op1 <= 32'd0;
        end
        else if (input_valid && !input_sel)
        begin
            mult_op1 <= input_value;
        end
    end
    
    //当input_sel为1时，表示输入数为乘数2
    always @(posedge clk)
    begin
        if (!resetn)
        begin
            mult_op2 <= 32'd0;
        end
        else if (input_valid && input_sel)
        begin
            mult_op2 <= input_value;
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
            6'd1 :
            begin
                display_valid <= 1'b1;
                display_name  <= "M_OP1";
                display_value <= mult_op1;
            end
            6'd2 :
            begin
                display_valid <= 1'b1;
                display_name  <= "M_OP2";
                display_value <= mult_op2;
            end
            6'd3 :
            begin
                display_valid <= 1'b1;
                display_name  <= "PRO_H";
                display_value <= product_r[63:32];
            end
            6'd4 :
            begin
                display_valid <= 1'b1;
                display_name  <= "PRO_L";
                display_value <= product_r[31: 0];
            end
            default :
            begin
                display_valid <= 1'b0;
                display_name  <= 48'd0;
                display_value <= 32'd0;
            end
        endcase
    end
//-----{输出到触摸屏显示}end
//----------------------{调用触摸屏模块}end---------------------//
endmodule
