function [Alpha] = Forward(a, b, p, X)

N= size(X,1);
%T= length( X(1,:) ); each sequence has different sections
K= size(p,1);
M= size(b,2); % nVoc emissions

Alpha= cell(N,1);

for m= 1: N
    T= size(X{m},1);
    al= zeros(T,K);
    for i= 1: K
        al(1,i)= 1; %init by 1
    end
    
    for t= 1:T-1
        for i=1:K
            sum=0;
            for j=1:K
                sumB=1;
                for u= 1: M
                    if X{m}(t+1,u)~=0
                        sumB= sumB* b(i,u)^X{m}(t+1,u);
                    end
                end
                sum= sum+ sumB* a(j,i) *al(t,j);
            end
            al(t+1,i)= sum;
        end
        disp('once t:');
        disp(t);
    end
    
    Alpha{m}= al;
end


