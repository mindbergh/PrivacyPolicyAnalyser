function [x] = generate(a, b, p, T)
  
  
K= length( a(1,:) );
M= length( b(1,:) );

prob=zeros(1,K+1);
for i=2:K+1
  prob(i)= prob(i-1)+p(i-1);
end

r= rand;
bg=0;
for i= 1:K
  if  r>=prob(i) && r< prob(i+1)
    bg= i;
    break;
  end
end

sts=zeros(1,T);
x=zeros(1,T);
sts(1)= bg;

for i=2:T
  prob=zeros(1,K+1);
  for k=2:K+1
    prob(k)= a(sts(i-1),k-1)+prob(k-1);
  end
  r= rand;
  for k= 1:K
    if  r>=prob(k) && r< prob(k+1)
      sts(i)= k;
      break;
    end
  end
end

for i=1:T
  prob=zeros(1,M+1);
  for k=2:M+1
    prob(k)= b(sts(i),k-1)+prob(k-1);
  end
  r= rand;
  for k= 1:K
    if  r>=prob(k) && r< prob(k+1)
      x(i)= k;
      break;
   end
  end
end

