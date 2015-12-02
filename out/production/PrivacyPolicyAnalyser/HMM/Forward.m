function [Alpha] = Forward(a, b, p, X)

N= length( X(:,1) );
T= length( X(1,:) );
K= length( a(1,:) );
M= length( b(1,:) );

Alpha= cell(N,1);

for m= 1: N
    al= zeros(T,K);
    for i= 1: K
        al(1,i)= p(i,1)* b(i, X(m,1) );
    end
    
    for t= 1:T-1
        for i=1:K
            sum=0;
            for j=1:K
                sum= sum+ b(i,X(m,t+1) )* a(j,i) *al(t,j);
            end
            al(t+1,i)= sum;
        end
    end
    
    Alpha(m,1)= al;
end


