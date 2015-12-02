function [Beta] = Backward(a, b, p, X)
N= length( X(:,1) );
T= length( X(1,:) );
K= length( a(1,:) );
M= length( b(1,:) );

Beta= cell(N,1);

for m= 1: N
    bt= zeros(T,K);
    for i= 1: K
        bt(T,i)= 1;
    end
    
    for t= T-1:-1:1
        for i=1:K
            sum=0;
            for j=1:K
                sum= sum+ a(i,j)*b(j,X(m,t+1) )*bt(t+1,j);
            end
            bt(t,i)= sum;
        end
    end
    
    Beta(m,1)= bt;
end
