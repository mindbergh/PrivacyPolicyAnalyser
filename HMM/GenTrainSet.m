function [X,nDocs,maxSessions] = GenTrainSet()

fid= fopen('XTrain');
tline = fgets(fid);
X= cell(1,1);
nSessions=0;
nDocs=0;
maxSessions=0;
tmpLine= zeros(100,3400);
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
        X{nDocs}= tmpDoc;
        if nSessions> maxSessions
            maxSessions=nSessions;
        end
        tmpLine= zeros(100,3400);
        nSessions=0;
    end
    
    tline = fgets(fid);
end

fclose(fid);

    



