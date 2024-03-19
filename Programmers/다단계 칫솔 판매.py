import math

class Sale:
    def __init__(self, val, ref):
        self.val = val
        self.ref = ref

    def __repr__(self):
        return f"val={self.val} ref={self.ref} profit={self.profit}"
        
        
def solution(enroll, referral, seller, amount):
    answer = [0] * len(enroll)
    
    seller_dict = {}
    sales = []
    sales.append(Sale(0, None))     #center
    
    #판매원별 추천인, 이익 초기화
    for idx, enr in enumerate(enroll):
        val = idx+1
        ref = referral[idx]
        seller_dict[enr] = val
        
        if ref == "-":
            sales.append(Sale(val, 0))    
        else:
            sales.append(Sale(val, seller_dict.get(ref)))
            
    for idx, seller_name in enumerate(seller):
        sell_amt = amount[idx] * 100
        seller_idx = seller_dict.get(seller_name)
        
        while seller_idx != 0:
            if sell_amt == 0:   #분배할 이익이 없는 경우 
                break
            per10 = math.floor(sell_amt/10)
            ref = sales[seller_idx].ref
            
            answer[seller_idx-1] += (sell_amt - per10)  #수익 더하기
        
            seller_idx = ref
            sell_amt = per10
    
    return answer
