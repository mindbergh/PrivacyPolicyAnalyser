function [a,b,p]=initParas(K,nVoc)

%a is init transition Prob K*K matrix
%each is initiated by 1/K
a= zeros(K,K);
for i =1:K
    for j=1:K
       a(i,j)= 0.9;
    end
end

%b is emission prob K*nVoc matrix
% each is initiated by 1/nVoc
b= zeros(K,nVoc);
for i=1:K
    for j=1:nVoc
        b(i,j)= 0.9;
    end
end

%p is init state Prob K*1 matrix
% each is initiated by 1/K
p= zeros(K,1);
for i=1:K
    p(i)=0.5;
end

    