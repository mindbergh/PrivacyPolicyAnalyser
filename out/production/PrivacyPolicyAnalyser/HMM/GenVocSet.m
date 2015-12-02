function [voc] = GenVocSet()

fid= fopen('LabeledVoc.txt');
tline = fgets(fid);

while ischar(tline)
   
    C = strsplit(tline);