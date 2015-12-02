function [Gamma, Xi] = E_step(a, b, p, X)

N= size(X,1);
%T= length( X(1,:) ); each sequence has different sections
K= size(p,1);
M= size(b,2); % nVoc emissions

A= Forward(a,b,p,X);
disp('finish forward')
B= Backward(a,b,p,X);
disp('finish backward')

Gamma= cell(N,1);
Xi= cell(N,1);
for m= 1:N
  T= size(X{m},1);
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
     SumB= zeros(K,1); % record last k's sumB s
                          %SumB(j) records b(j,X(m,t) )
     for j=1:K
        SumB(j)= 1;
          for u=1:M
             if X{m}(t,u) ~=0
                 SumB(j)=SumB(j)* b(j,u)^X{m}(t,u);
             end
          end
     end
        %SumB
    for i=1:K
      for j=1:K
        nome= A{m}(t-1,i)* a(i,j)*SumB(j)*B{m}(t,j);
        deno=0;
        for y= 1:K
          deno=deno+ A{m}(t-1,y)*B{m}(t-1,y);
        end
        xi(t,i,j)= nome/deno;
      end
    end
   end
   
   Gamma{m}= gm;
   Xi{m}= xi;
end
   
   