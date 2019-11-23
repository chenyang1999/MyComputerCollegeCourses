# import check

EPSILON = 0.0000001


class Item:
    def __init__(self, n, c, quant, val):
        '''
    Fields: name (Str), code (Str), quantity (Nat), cost (Float)
    requires: cost >= 0.0
              code is of the form L## where L is any upper case letter 
              and the two # symbols represent digits.
              codes do not repeat.
              
    __init__: Item Str Str Nat Float -> None
    '''
        self.name = n
        self.code = c
        self.quantity = quant
        self.cost = val

    def __repr__(self):
        '''
    Returns a representation of an Item, specifically the 
    fields in the item are returned in a string.
    
    __repr__: Item -> Str
    '''
        s = 'Name: {0.name}\nCode: {0.code}\n' + \
          'Quantity: {0.quantity}\nCost: {0.cost}'
        return s.format(self)

    def __eq__(self, other):
        '''
    Returns True if and only if self and other are both Items
    with identical fields (costs equal up to EPSILON)
    
    __eq__: Item Any -> Bool
    '''
        return isinstance(other, Item) and \
               self.name == other.name and\
               self.code == other.code and\
               self.quantity == other.quantity and\
               abs(self.cost - other.cost) <= EPSILON


class Vending_Machine:
    def __init__(self, list_of_items, starting_money):
        '''
    Fields: items (listof Item) money (Float)
    
    __init__ Vending_Machine (listof Item) Float -> None
    requires: starting_money >= 0.0
    '''
        self.items = list_of_items
        self.money = starting_money

    def __repr__(self):
        '''
    Returns a representation of an Vending_Machine, specifically the 
    fields Items and Money are Returned.
    
    __repr__ : Vending_Machine -> Str
    '''
        return 'Money: {0.money}\nItems: {0.items}'.format(self)

    def __eq__(self, other):
        '''
    Returns True if and only if self and other have the same
    items and same amount of money up to EPSILON.
    
    __eq__ Vending_Machine Any -> Bool
    '''
        return isinstance(other, Vending_Machine) and \
               abs(self.money - other.money) <= EPSILON and\
               self.items == other.items

    def out_of_item(self, name):
        '''
    Returns True if and only if either there is no item
    in self.items with the name field equal to name or
    if all such items have no quantity and False otherwise.
    
    out_of_item: Vending_Machine Str -> None
    '''
        s = 0
        for item in self.items:
            if item.name == name:
                s = s + item.quantity
        return s == 0

    #YOUR CODE GOES BELOW:
    # def out_of_item(self, name):
    #   pass

    def purchase(self, code):
        for i in self.items:
            if i.code == code and i.quantity > 0:
                i.quantity -= 1
                self.money += i.cost
                return "Thanks!"
        return "ERROR"

    def update_cost(self, name, new_cost):
        for i in self.items:
            if i.name == name:
                i.cost = new_cost
                return True
        return False

    def replenish(self, pairs):
        for k, v in pairs.items():
            for i in self.items:
                if i.code == k:
                    i.quantity += v
        ans = 0
        for i in self.items:
            ans = ans + i.quantity
        return ans


items = [Item('Coke  Zero', 'A01', 10, 1.25), Item('Sprite', 'A02', 1, 1.00)]
v = Vending_Machine(items, 1000.0)
print(v.purchase('A02'))
print(v.out_of_item('Sprite'))
print(v.money)
print(v.update_cost('Coke  Zero', 1.00))
print(v.items[0].cost)
print(v.replenish({'A01': 1, 'A02': 10}))
print(v.items[0].quantity)
