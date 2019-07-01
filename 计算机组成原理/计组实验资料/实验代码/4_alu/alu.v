`timescale 1ns / 1ps
//*************************************************************************
//   > 文件名: alu.v
//   > 描述  ：ALU模块，可做12种操作
//   > 作者  : LOONGSON
//   > 日期  : 2016-04-14
//*************************************************************************
module alu(
    input  [11:0] alu_control, 
    input  [31:0] alu_src1, 
    input  [31:0] alu_src2,
    output [31:0] alu_result 
    );

    wire alu_add;
    wire alu_sub; 
    wire alu_slt; 
    wire alu_sltu;
    wire alu_and;
    wire alu_nor;
    wire alu_or;
    wire alu_xor;
    wire alu_sll;
    wire alu_srl;
    wire alu_sra;
    wire alu_lui; 

    assign alu_add  = alu_control[11];
    assign alu_sub  = alu_control[10];
    assign alu_slt  = alu_control[ 9];
    assign alu_sltu = alu_control[ 8];
    assign alu_and  = alu_control[ 7];
    assign alu_nor  = alu_control[ 6];
    assign alu_or   = alu_control[ 5];
    assign alu_xor  = alu_control[ 4];
    assign alu_sll  = alu_control[ 3];
    assign alu_srl  = alu_control[ 2];
    assign alu_sra  = alu_control[ 1];
    assign alu_lui  = alu_control[ 0];

    wire [31:0] add_sub_result;
    wire [31:0] slt_result;
    wire [31:0] sltu_result;
    wire [31:0] and_result;
    wire [31:0] nor_result;
    wire [31:0] or_result;
    wire [31:0] xor_result;
    wire [31:0] sll_result;
    wire [31:0] srl_result;
    wire [31:0] sra_result;
    wire [31:0] lui_result;

    assign and_result = alu_src1 & alu_src2;     
    assign or_result  = alu_src1 | alu_src2;     
    assign nor_result = ~or_result;             
    assign xor_result = alu_src1 ^ alu_src2;    
    assign lui_result = {alu_src2[15:0], 16'd0}; 

    wire [31:0] adder_operand1;
    wire [31:0] adder_operand2;
    wire        adder_cin     ;
    wire [31:0] adder_result  ;
    wire        adder_cout    ;
    assign adder_operand1 = alu_src1; 
    assign adder_operand2 = alu_add ? alu_src2 : ~alu_src2; 
    assign adder_cin      = ~alu_add;  
    adder adder_module(
    .operand1(adder_operand1),
    .operand2(adder_operand2),
    .cin     (adder_cin     ),
    .result  (adder_result  ),
    .cout    (adder_cout    )
    );

    //请于此处添加代码
	
endmodule
