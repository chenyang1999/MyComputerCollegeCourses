#!/bin/bash

# Maintained by Xiaodong Wang

# This file include 2 process: 
# The first: convert all the svg file to eps file using inkscape
# The second: excute make with ./Makefile
# So, if you create a new svg file, 
# you can use the file to accomplish the whole process.

# If you want to export drawing area, below command can be used.
# inkscape -D -f soc_in_home.pdf -A soc_in_home.1.pdf

# Refer inkscape man pages for details.

for file in `ls figures/svg`; do
    if [ figures/svg/$file -nt figures/${file%.*}.pdf ]; then
	echo  "$file is updated"
	inkscape -f figures/svg/$file --export-pdf=figures/${file%.*}.pdf
    fi
done

echo "All eps file list:"
ls figures/*.pdf

echo "Make process ..."
make clean
make

