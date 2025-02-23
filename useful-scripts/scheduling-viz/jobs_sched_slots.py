"""
Plots jobs scheduled slot [arrival time, arrival time + exec time], assuming execution on a single CPU
"""

import argparse
import seaborn as sb
import pandas as pd
import matplotlib.pyplot as pl
from typing import List
from os.path import basename, splitext


def main(filenames: List[str], names: List[str] = None):
  if names:
    assert len(filenames) == len(names), "Mismatching length of names and filenames"
  else:
    names = [splitext(basename(filename))[0] for filename in filenames]

  data_frames = []
  for filename, name in zip(filenames, names):
    with open(filename, "r") as f:
      data = pd.read_csv(f)
      data["job"] = name
      data_frames.append(data)
  
  df = pd.concat(data_frames)

  sb.set_theme(style="whitegrid")
  pl.hlines(
    y=df["job"],
    xmin=df["time"],
    xmax=df["time"]+df["exec_time"],
    linewidth=10
  )
  sb.despine(left=True, bottom=True)
  pl.tight_layout()
   
  # g.despine(left=True)
  # g.set_axis_labels("Job", "Time [us]")
  # g.legend.set_title("Scheduling vs Time")

  pl.show()


if __name__ == "__main__":
  parser = argparse.ArgumentParser(
    prog="jobs_sched_slots.py",
    description="Plots jobs scheduled slot [arrival time, arrival time + exec time], assuming execution on a single CPU"
  )
  
  parser.add_argument(
    "filenames",
    metavar="FILENAME",
    nargs="+",
    help="CSV input filename, representing a job, organized with \"time, exec_time, cpu\" columns."
  )

  parser.add_argument(
    "--names",
    metavar="JOB NAME",
    nargs="+",
    help="Job name. If not specified file name is used.",
    default=None
  )

  args = parser.parse_args()
  main(args.filenames, args.names)