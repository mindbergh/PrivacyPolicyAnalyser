function [a, b, p] = M_step(Gamma, Xi, X, M, K)

N= length( X(:,1) );
T= length( X(1,:) );

a= zeros(K,K);
p= zeros(K,1);

for i=1:K
  for j=1:K
      nome=0;
      for m=1:N
        for t=1:T
          nome=nome+ Xi{m}(t,i,j);
        end
      end
      
      deno= 0;
      for k=1:K
        for m=1:N
          for t=1:T
            deno= deno+ Xi{m}(t,i,k);
          end
        end
      end
      
      a(i,j)=nome/deno;
    end
end

for i=1:K
  nome=0;
  for m=1:N
    nome= nome+ Gamma{m}(1,i);
  end
  deno= m;
  p(i,1)= nome/deno;
end

B= zeros(K,M);
b= zeros(K,M);
for k=1:K
  for j= 1:M
    sum=0;
    for m=1:N
      for t=1:T
        if X(m,t)== j
          sum=sum+ Gamma{m}(t,k);
        end
      end
    end
    B(k,j)= sum;
  end
end

for k=1:K
  for j=1:M
    nome= B(k,j);
    deno=0;
    for i=1:M
      deno=deno+ B(k,i);
    end
    b(k,j)=nome/deno;
  end
end
