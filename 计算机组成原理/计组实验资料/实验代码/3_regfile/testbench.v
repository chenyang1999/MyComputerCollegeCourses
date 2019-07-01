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
	reg wen;
	reg [4 :0] raddr1; 
	reg [4 :0] raddr2;
	reg [4 :0] waddr;
	reg [31:0] wdata;
	reg [4 :0] test_addr;
	
    // Outputs
    wire [31:0] rdata1;
	wire [31:0] rdata2;
	wire [31:0] test_data;

    // Instantiate the Unit Under Test (UUT)
    regfile rf(
    .clk(clk),
    .wen(wen),
    .raddr1(raddr1),
    .raddr2(raddr2),
    .waddr(waddr),
    .wdata(wdata),
    .rdata1(rdata1),
    .rdata2(rdata2),
    .test_addr(test_addr),
    .test_data(test_data)
    );

    initial begin
        // Initialize Inputs
        clk = 0;
        wen = 0;
        raddr1 = 0;
        raddr2 = 0;
		waddr = 0;
		wdata = 0;
		test_addr = 0;

        // Wait 100 ns for global reset to finish
        #100;
		waddr = 5'h05;
		wdata = 32'h3F;
        #400;
        wen = 1'b1;
        #500;
        wen = 1'b0;
        #400;
        raddr1 = 5'h05;
        #500;
        raddr2 = 5'h05;
        #400;
        test_addr = 5'h05;
        // Add stimulus here
    end
   always #5 clk = ~clk;
endmodule

