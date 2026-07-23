import { accountTypeLabels } from '../../utils/formatAccountLabel';

function AccountItem({ account }) {
    const { bankName, accountType, balance, currency, accountOpenedAt } = account;

    return (
        <tr>
            <td>{bankName}</td>
            <td>{accountTypeLabels[accountType]}</td>
            <td>{balance}</td>
            <td>{currency} {balance.toFixed(2)}</td>
            <td>{accountOpenedAt && <p>Opened: {accountOpenedAt}</p>}</td>
        </tr>
    );
}

export default AccountItem;