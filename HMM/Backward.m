function [Beta] = Backward(a, b, p, X)

N= size(X,1);
%T= length( X(1,:) ); each sequence has different sections
K= size(p,1);
M= size(b,2); % nVoc emissions

Beta= cell(N,1);

for m= 1: N
    T= size(X{m},1);
    bt= zeros(T,K);
    for i= 1: K
        bt(T,i)= 1;
    end
    
    for t= T-1:-1:1
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
                sum= sum+ a(i,j)*SumB(j)*bt(t+1,j);
            end
            bt(t,i)= sum;
        end
        Str = sprintf('backward:%d doc,%d segment',m,t);
        disp(Str);
    end
    
    Beta{m}= bt;
end
