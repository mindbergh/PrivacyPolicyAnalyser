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
        SumB= zeros(K,1); % record last k's sumB s
                          %SumB(j) records b(j,X(m,t+1) )
        for j=1:K
            SumB(j)= 1;
            for u=1:M
                if X{m}(t+1,u) ~=0
                    SumB(j)=SumB(j)* b(j,u)^X{m}(t+1,u);
                end
            end
        end
        
        for i=1:K
            sum=0;
            for j=1:K
                sum= sum+ SumB(i)* a(j,i) *al(t,j);
            end
            al(t+1,i)= sum;
        end
        
        Str = sprintf('forward:%d doc,%d segment',m,t);
        disp(Str);
    end
    
    Alpha{m}= al;
end


