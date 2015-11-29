function [a,b,p] = EM_estimate(a, b, p, X, nIter)

K= length( a(1,:) );
M= length( b(1,:) );
for i=1:nIter
  [Gamma, Xi] = E_step(a, b, p, X);
  [a, b, p] = M_step(Gamma, Xi, X, M, K);
end
