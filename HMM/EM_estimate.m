function [a,b,p] = EM_estimate(a, b, p, X, nIter)

K= size(a,1);
M= size(b,2);

nIter=1;
for i=1:nIter
  [Gamma, Xi] = E_step(a, b, p, X);
  [a, b, p] = M_step(Gamma, Xi, X, M, K);
  disp('finish iteration once');
end

