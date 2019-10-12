# Last updated Feb 4, 2019
"""
    This module is used for testing python code in CS 116 and CS 234

    The useful functions in this module are:
    * check.within, for testing functions whose output includes floats
    * check.expect, for testing all other functions
    * check.set_screen, for testing screen output (print statements)
    * check.set_input, for testing keyboard input (raw_input)
    * check.set_file, for testing file output

    For details on using these functions, please read
    the Python Style guide from the CS 116 website,
    www.student.cs.uwaterloo.ca/~cs116/styleGuide
"""

import sys, os, builtins
backup_stdin = sys.stdin
backup_stdout = sys.stdout
old_input = builtins.input


# redefine input to hide prompts
def blank_input(prompt = None):
    return old_input()

class InputError(Exception):
    """
    Not enough parameters given to set_input.
    """
    def __init__(self):
        self.val = "not enough parameters to set_input"
    def __str__(self):
        return self.val
    

class redirect_input:
    """
    Keyboard input is redirected from this class
    whenever set_input is called.
    """
    def __init__(self, inp):
        self.lst = inp
    def readline(self):
        if self.lst:
            return self.lst.pop(0)
        else:
            raise InputError()

class redirect_output:
    """
    Screen output is redirected to this class
    whenever set_screen is called.
    """
    def __init__(self):
        self.screen = ""
    def __str__(self):
        return self.screen
    def __nonzero__(self):
        return bool(self.screen)
    def write(self, string):
        self.screen += string
    def reset(self):
        self.screen = ""

expected_screen = ""
actual_screen = redirect_output()
test_output = redirect_output()
input_list = []
file_list = []
dir_list = []
exact_screen = False


def set_screen(string):
    """
    Consumes a description of the expected screen output
    for the next call to check.expect or check.within.
    """
    global expected_screen
    expected_screen = str(string)
    sys.stdout = actual_screen
    
    builtins.input = blank_input

def set_print_exact(*strlst):
    """
        Consumes a list of strings in the expected exact output
        for the next call to check.expect or check.within.
        """
    global expected_screen, exact_screen
    expected_screen = '\n'.join(strlst)
    exact_screen = True
    sys.stdout = actual_screen
    
    builtins.input = blank_input
    
    

def set_input(*inputs):
    """
    Consumes a variable amount of strings representing keyboard input for
    the next call to check.expect or check.within. Python treats *inputs
    as a tuple.
    """
    global input_list
    #if type(lst) != list:
    #    raise TypeError("set_input must consume a list")
    for i in inputs:
        if type(i) != str:
            raise TypeError("all parameters must be strings")
    input_list = list(map(lambda s: s+"\n", inputs))
    
    
    sys.stdin = redirect_input(input_list)
    sys.stdout = actual_screen

def set_file(resulting_file, expected_file):
    """
    Consumes two strings: resulting_file (the name
    of a file that will be produced by the function)
    and expected_file (the name of a file to whose
    contents will match resulting_file if the function
    works correctly). Checks that the files contain the
    same text, ignoring whitespace, on the next call
    to check.expect or check.within.
    """
    global file_list, dir_list
    dir_list = os.listdir(os.getcwd())
    file_list.append((resulting_file, expected_file, False))

def set_file_exact(resulting_file, expected_file):
    """
    Consumes two strings: resulting_file (the name
    of a file that will be produced by the function)
    and expected_file (the name of a file to whose
    contents will match resulting_file if the function
    works correctly). Checks that the files contain the
    same text, including whitespace, on the next call
    to check.expect or check.within.
    """
    global file_list, dir_list
    dir_list = os.listdir(os.getcwd())
    file_list.append((resulting_file, expected_file, True))

def expect(label, function_call, expected_value):
    """
    Testing function for equality. Will also print the
    description given to set_screen, use the keyboard
    input given to set_input, and compare files given
    to set_files.
    """
    run_test(label, function_call, expected_value, None)

def within(label, function_call, expected_value, acceptable_tolerance):
    """
    Testing function for floating point numbers. Will also
    print the description given to set_screen, use the
    keyboard input given to set_input, and compare files
    given to set_files.
    """
    run_test(label, function_call, expected_value, acceptable_tolerance)

def run_test(label, result, expected, tolerance):
    """
    Performs the tests given to check.within and check.expect.
    Do not use run_test in your code for CS 116.
    """
    global actual_screen, expected_screen, input_list, file_list, dir_list, exact_screen
    sys.stdout = test_output
    
    
    if input_list:
        print ("{0}: FAILED; not all input strings were used\n".format(label))
    elif type(result) != type(expected):
        rtype = str(type(result)).strip('<class> ')
        etype = str(type(expected)).strip('<class> ')
        print ("{0}: FAILED; {1} is a {2}, whereas {3} is a {4}\n".format(label,expected,etype,result,rtype))
    elif tolerance and not approx(result, expected, tolerance):
        print ("{0}: FAILED; expected {1}, saw {2}\n".format(label, expected, result))
    elif not(tolerance) and result != expected:
        print ("{0}: FAILED; expected {1}, saw {2}\n".format(label, expected, result))
    else:
    	print ("{0}: PASSED\n".format(label))
    
    if file_list:
        new_files = []
        for tup in file_list:
            new_label = "{0} {1}".format(label, tup[0:2])
            compare_files(new_label, new_files, tup[0], tup[1], tup[2])
        extra_files = list(set(os.listdir(os.getcwd())) ^ set(dir_list + new_files) )
        if extra_files:
            print ("{0}: The following additional files were created: {1}".format(label, ", ".join(extra_files)))
    
    if exact_screen:
        actual_screen = str(actual_screen)[:-1]
        if expected_screen != actual_screen:	    
            print("{0} - print output: FAILED; expected\n{1}\nsaw\n{2}\n".format(label, expected_screen, actual_screen))
        else:
            print("{0} - print output: PASSED\n".format(label))
        actual_screen = redirect_output()
    elif expected_screen:
        print ("{0} (expected screen output):".format(label))
        print (expected_screen)
        print ("")
        print ("{0} (actual screen output):".format(label))
        print (actual_screen)

    input_list, file_list, dir_list = [], [], []
    expected_screen = ""
    exact_screen = False
    actual_screen.reset()
    
    sys.stdin = backup_stdin
    sys.stdout = backup_stdout
    builtins.input = old_input
    if test_output:
        print (str(test_output).strip())
        print ("-----")
    test_output.reset()

def approx(result, expected, tolerance):
    """
    Performs approximate equality comparisons for check.within.
    Do not use approx in your code for CS 116.
    """
    if type(result) != type(expected):
        return False
    
    tp = type(result)
    
    if tp == float:
        return abs(result - expected) <= tolerance
    elif tp == complex:
        return abs(result.real - expected.real) <= tolerance and \
               abs(result.imag - expected.imag) <= tolerance
    elif tp in (list, tuple): # sequences that can contain floats
        return len(result) == len(expected) and \
               all(map(approx, result, expected, [tolerance]*len(result)))
    elif tp in (dict, type(redirect_output()), set, frozenset):
    # unordered containers that can contain floats
    # for whatever reason, 'instance' is not a type, so type(redirect_output) is used instead
        if tp == dict:
            result = result.items()
            expected = expected.items()
        elif tp == type(redirect_output()):
            # check that both instances are of the same class
            if result.__class__ != expected.__class__:
                return False
            result = result.__dict__.items()
            expected = expected.__dict__.items()
        # if tp in (set, frozenset) then no action required
        return approx(sorted(result), sorted(expected), tolerance)
    else: # anything that hits else cannot contain floats
        return result == expected

def compare_files(label, new_files, fname1, fname2, exact):
    """
    Performs file comparisons for check.within and check.expect.
    Do not use compare_files in your code for CS 116.
    """
    try:
        f = open(fname1, 'r')
        lines1 = list(map(lambda x: x.strip(), f.readlines()))
        f.close()
        new_files.append(fname1)
    except IOError:
        print ("{0}: File {1} does not exist\n".format(label, fname1))
        return None
    try:
        f = open(fname2, 'r')
        lines2 = list(map(lambda x: x.strip(), f.readlines()))
        f.close()
        new_files.append(fname2)
    except IOError:
        print ("{0}: File {1} does not exist\n".format(label, fname2))
        return None
    
    if lines1 == [] and lines2 == []:
        return None
    elif lines1 == []:
        print ("{0}: {1} is empty but {2} is not.".format(label, fname1, fname2))
        print ("{0} (line 1): {1}\n".format(fname2, lines2[0]))
        return None
    elif lines2 == []:
        print ("{0}: {1} is empty but {2} is not.".format(label, fname2, fname1))
        print ("{0} (line 1): {1}\n".format(fname1, lines1[0]))
        return None
    
    while lines1[-1].isspace() or lines1[-1] == "":
        lines1.pop()
    while lines2[-1].isspace() or lines2[-1] == "":
        lines2.pop()
    
    if len(lines1) != len(lines2):
        print ("{0}: {1} and {2} do not contain the same number of lines.".format(label, fname1, fname2))
    
    n = min(len(lines1), len(lines2))
    bad_lines = []
    
    for i in range(n):
        if exact:
            line1 = lines1[i].rstrip()
            line2 = lines2[i].rstrip()
        else:
            line1 = "".join(lines1[i].split())
            line2 = "".join(lines2[i].split())
        if line1 != line2:
            bad_lines.append(i+1)
    
    if bad_lines:
        first_line = bad_lines[0]
        bad_lines = ", ".join(map(lambda x: str(x), bad_lines))
        print ("{0}: The following lines in {1} and {2} do not match: {3}".format(label, fname1, fname2, bad_lines))
        
        extra_spaces = " " * abs(len(fname1) - len(fname2))
        if len(fname1) < len(fname2):
            fname1 += extra_spaces
        else:
            fname2 += extra_spaces
        
        print ("{0} (line {1}): {2}".format(fname1, first_line, lines1[first_line-1]))
        print ("{0} (line {1}): {2}".format(fname2, first_line, lines2[first_line-1]))
    
    if len(lines1) != len(lines2) or bad_lines:
        print ("")

