function [Gamma, Xi] = E_step(a, b, p, X)
N= length( X(:,1) );
T= length( X(1,:) );
K= length( a(1,:) );
M= length( b(1,:) );

A= Forward(a,b,p,X);
B= Backward(a,b,p,X);
Gamma= cell(N,1);
Xi= cell(N,1);
for m= 1:N
  gm= zeros(T,K);
  xi= zeros(T,K,K);
  for t= 1:T
    for i= 1:K
        nome= A{m}(t,i)*B{m}(t,i);
        deno=0;
        for j=1:K
          deno=deno+ A{m}(t,j)*B{m}(t,j);
        end
        gm(t,i)= nome/deno;
    end
   end
   
   for t=2:T
    for i=1:K
      for j=1:K
        nome= A{m}(t-1,i)* a(i,j)*b(j,X(m,t))*B{m}(t,j);
        deno=0;
        for y= 1:K
          deno=deno+ A{m}(t-1,y)*B{m}(t-1,y);
        end
        xi(t,i,j)= nome/deno;
      end
    end
   end
   
   Gamma(m,1)= gm;
   Xi(m,1)= xi;
end
   
   