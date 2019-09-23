import React from "react";
import TableHeader from "./tableheader";
import TableBody from "./tableBody";

const Table = ({ columns, data, onSort, sortColumn }) => {
  // destructuring the props argument directly!
  return (
    <table className="table">
      <TableHeader columns={columns} sortColumn={sortColumn} onSort={onSort} />
      <TableBody data={data} columns={columns} />
    </table>
  );
};

export default Table;
