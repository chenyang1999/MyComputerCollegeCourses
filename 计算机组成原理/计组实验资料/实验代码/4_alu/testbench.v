`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   17:13:38 04/15/2016
// Design Name:   multiply
// Module Name:   F:/new_lab/multiply/tb.v
// Project Name:  multiply
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: multiply
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module tb;

    // Inputs
    reg clk;
    reg [11:0] alu_control;
    reg [31:0] alu_src1;
    reg [31:0] alu_src2;

    // Outputs
    wire [31:0] alu_result;

    // Instantiate the Unit Under Test (UUT)
alu al(
    input  [11:0] alu_control, 
    input  [31:0] alu_src1, 
    input  [31:0] alu_src2,
    output [31:0] alu_result 
    );

    initial begin
        // Initialize Inputs
        clk = 0;
        alu_control = 0;
        alu_src1 = 32'H00001111;
		alu_src2 = 32'H00001111;

        // Wait 100 ns for global reset to finish
        #100;
        alu_control = 12'b0000_0000_0001;
        #400;
        alu_control = 12'b0000_0000_0010;
        #500;
        alu_control = 12'b0000_0000_0100;
        #400;
        alu_control = 12'b0000_0000_1000;
        #500;
        alu_control = 12'b0000_0001_0000;
        #400;
        alu_control = 12'b0000_0010_0000;
        #500;
        alu_control = 12'b0000_0100_0000;
        #400;
        alu_control = 12'b0000_1000_0000;
		#400;
        alu_control = 12'b0001_0000_0000;
		#400;
        alu_control = 12'b0010_0000_0000;
		#400;
        alu_control = 12'b0100_0000_0000;
		#400;
        alu_control = 12'b1000_0000_0000;
        // Add stimulus here
    end
   always #5 clk = ~clk;
endmodule

