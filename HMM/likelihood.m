function [score] = likelihood(a, b, p, Xtest)

N= length( Xtest(:,1) );
T= length( Xtest(1,:) );
K= length( a(1,:) );
M= length( b(1,:) );

score= zeros(N,1);
A = Forward(a, b, p, Xtest);
for n=1:N
  sum=0;
  for i=1:K
    sum= sum+ A{n}(T,i);
  end
  score(n,1)=log(sum);
end

  