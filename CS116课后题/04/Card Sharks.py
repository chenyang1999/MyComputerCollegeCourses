cards=[1,2,3,1,5,5]
def bonus_round(cards):
	money=1000
	print(cards)
	print('Your first card is a {}.'.format(cards[0]))
	i=1 # i 表示用于循环下一张卡片
	L=len(cards)
	while i<L :
		bet=int(input('Enter a wager between 0 and {}: '.format(money))) #每次下注的钱数
		while bet<0 or bet >money:
			bet=int(input('Enter a wager between 0 and {}: '.format(money))) #不能下注超过本金,超过了要重新下注
		buy=input('Is the next card higher or lower than {}: '.format(cards[i-1])) #每次买大买小
		while buy not in ['lower','higher']:
			buy=input('Is the next card higher or lower than {}: '.format(cards[i-1])) #必须买大或者买小,不能输入其他指令
		print("The card is a {}.".format(cards[i]))
		win=False #当前轮的胜负标记
		if buy=='higher':
			if cards[i]>cards[i-1]:
				win=True
		if buy=='lower':
			if cards[i]<cards[i-1]:
				win=True
		# 出现平局
		if cards[i]==cards[i-1]:
			bet=0
			print(f"Push. You now have {money} dollars.")
			continue
		if win:
			money=money+bet
			print(f"You are correct! You now have {money} dollars.")
		else:
			money=money-bet
			print(f"You are incorrect. You now have {money} dollars.")
#		print(i)
		i=i+1
		
if __name__=="__main__":
	bonus_round([1,2,3,4,5])