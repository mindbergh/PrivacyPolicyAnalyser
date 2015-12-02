function [a,b,p,K,maxSessions,nDocs,nVoc,voc,X,nIter]=initTrainData()

K=14;
nIter=20;
[voc,nVoc] = GenVocSet();
[X,nDocs,maxSessions] = GenTrainSet(nVoc);
[a,b,p]=initParas(K,nVoc);

save('TrainMat');

end