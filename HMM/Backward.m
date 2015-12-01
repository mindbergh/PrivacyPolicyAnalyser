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
        for i=1:K
            sum=0;
            for j=1:K
                sumB=1;
                for u=1:M
                    if X{m}(t+1,u) ~=0
                        sumB=sumB* b(j,u)^X{m}(t+1,u);
                    end
                end
                sum= sum+ a(i,j)*sumB*bt(t+1,j);
            end
            bt(t,i)= sum;
        end
    end
    
    Beta{m}= bt;
end
