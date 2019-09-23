import React, { Component } from "react";
//import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

class TableHeader extends Component {
  raiseSort = column => {
    const sortColumn = { ...this.props.sortColumn };
    if (sortColumn.column === column) {
      // this is a direction change on the existing column sort
      sortColumn.order = sortColumn.order === "asc" ? "desc" : "asc";
      sortColumn.column = column;
    } else {
      // this is a new column sort in ascending order
      sortColumn.order = "asc";
      sortColumn.column = column;
    }

    this.props.onSort(sortColumn); // THIS LINE PASSES BACK sortColumn via onSort function, relating to movies.handleSorting
  };

  renderSortIcon = column => {
    const { sortColumn } = this.props;

    if (column.name !== sortColumn.column) return null;

    // RECOMMENDED, but not working:
    // if (sortColumn.order === "asc") return <i className="fas fa-sort-up" />; // FONTAWESOME NOT WORKING
    // return <i className="fa fa-sort-desc" />; // FONTAWESOME NOT WORKING

    // if (sortColumn.order === "asc") return <FontAwesomeIcon icon="sort-up" />; // FONTAWESOME NOT WORKING
    // return <i className="fa fa-sort-desc" />; // FONTAWESOME NOT WORKING
  };

  render() {
    return (
      <thead>
        <tr>
          {this.props.columns.map(column => (
            <th
              className="clickable" // see index.css
              key={column.name || column.key}
              onClick={() => this.raiseSort(column.name)}
            >
              {column.label}
            </th>
          ))}
        </tr>
      </thead>
    );
  }
}

export default TableHeader;

// {column.label} {this.renderSortIcon(column)}
