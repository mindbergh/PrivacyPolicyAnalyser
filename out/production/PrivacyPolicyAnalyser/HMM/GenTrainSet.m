function [X,nDocs,maxSessions,maxWords] = GenTrainSet()

fid= fopen('XTrain');
tline = fgets(fid);
X= cell(1,1);
nSessions=0;
nDocs=0;
maxSessions=0;
while ischar(tline)
    tmpLine= zeros(100,3400);
    C = strsplit(tline);
    if isempty(findstr(tline,'<enddoc>') )
        nSessions=nSessions+1;
        for i= 1:length(C)
           str=char( C(i) );
           if ~isempty(str)
                num= str2num(str);
                tmpLine(nSessions,num)= tmpLine(nSessions,num)+1;
           end
        end
    else
        nDocs=nDocs+1;
        X{nDocs}= tmpLine(1:nSessions,:);
        if nSessions> maxSessions
            maxSessions=nSessions;
        end
        nSessions=0;
    end
    
    tline = fgets(fid);
end

fclose(fid);

    



