export const accountTypeLabels = {
    CHEQUING: 'Chequing',
    SAVINGS: 'Savings',
    CREDIT: 'Credit Card',
    INVESTMENT: 'Investment'
};

export function formatAccountLabel(account) {
    return `${account.bankName} ${accountTypeLabels[account.accountType]}`;
}