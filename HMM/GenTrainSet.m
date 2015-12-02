function [X,nDocs,maxSessions] = GenTrainSet(nVoc)
%this function is used to generate X
%X is 1011*1 cell array, each cell is nSession*3400 matrix, recording the 
%number of appearance of eahc word in the vocabulary

fid= fopen('XTrain');
tline = fgets(fid);
Xtmp= cell(1011,1); %there are 1011 corpus
nSessions=0;
nDocs=0;
maxSessions=0;
tmpLine= zeros(100,nVoc);
while ischar(tline)
    C = strsplit(tline);
    if isempty(findstr(tline,'<enddoc>') )
        nSessions=nSessions+1;
        for i= 1:(length(C)-2)
           str=char( C(i) );
           if ~isempty(str)
                num= str2num(str);
                tmpLine(nSessions,num)= tmpLine(nSessions,num)+1;
           end
        end
    else
        nDocs=nDocs+1;
        tmpDoc= tmpLine(1:nSessions,:);
        Xtmp{nDocs}= tmpDoc;
        if nSessions> maxSessions
            maxSessions=nSessions;
        end
        tmpLine= zeros(100,nVoc);
        nSessions=0;
    end
    
    tline = fgets(fid);
end

X=cell(nDocs,1);
for i=1:nDocs
    X{i}= Xtmp{i};
end

fclose(fid);

    



