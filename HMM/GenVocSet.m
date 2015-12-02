function [voc,nVoc] = GenVocSet()

voc= cell(3095,1);
fid= fopen('LabeledVoc.txt');
tline = fgets(fid);
nVoc=0;
while ischar(tline)
    C = strsplit(tline);
    snum=char ( C(2) );
    num=str2num( snum );
    str= char( C(1) );
    voc{num}= str;
    nVoc=nVoc+1;
    tline = fgets(fid);
end

save 
