struct ListNode
{
    int val;
    struct ListNode *next;
};

struct ListNode *mergeTwoLists(struct ListNode *list1, struct ListNode *list2)
{

    if (list1 == nullptr)
    { // En el caso que la lista uno esta vacia, simplemente se regresa la lista 2
        return list2;
    }

    if (list2 == nullptr)
    { // En el caso que la lista dos esta vacia, simplemente se regresa la lista 1
        return list1;
    }

    if (list1->val <= list2->val)
    {
        list1->next = mergeTwoLists(list1->next, list2);
        return list1;
    }
    else
    {
        list2->next = mergeTwoLists(list1, list2->next);
        return list2;
    }
}