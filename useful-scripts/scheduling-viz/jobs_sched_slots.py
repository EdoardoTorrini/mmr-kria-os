"""
Plots jobs scheduled slot [arrival time, arrival time + exec time], assuming execution on a single CPU and times is nanoseconds.
"""

import argparse
import seaborn as sb
import pandas as pd
import matplotlib.pyplot as pl
from typing import List
from os.path import basename, splitext

VERT_SCALING_COEFF: float = 1.5

def main(filenames: List[str], names: List[str] = None):
  if names:
    assert len(filenames) == len(names), "Mismatching length of names and filenames"
  else:
    names = [splitext(basename(filename))[0] for filename in filenames]

  sb.set_theme(style="whitegrid", palette="pastel")
  data_frames = []
  palette = sb.color_palette("pastel")

  for i, (filename, name) in enumerate(zip(filenames, names)):
    with open(filename, "r") as f:
      data = pd.read_csv(f)
      data_frames.append(data)
    
    data["job"] = name
    data["time"] /= 1e6
    data["exec_time"] /= 1e6
    data["color"] = [palette[i % len(filenames)]] * len(data)
  
  df = pd.concat(data_frames)
  end_times = df["time"]+df["exec_time"]

  for job in df["job"].unique():
    job_idxes = df["job"] == job
    job_df = df[job_idxes]

    pl.hlines(
      y=[job] * len(job_df),
      xmin=job_df["time"],
      xmax=end_times[job_idxes],
      linewidth=10,
      color=job_df["color"],
      label=job
    )
  
  pl.ylim(-1, len(df["job"].unique())*VERT_SCALING_COEFF)
  pl.xlim(500e3, 500e3+100)


  pl.gca().set_xlabel("Time [ms]")
  pl.gca().set_ylabel("Job")
  pl.gca().set_title("Job scheduling vs Time")
  pl.gca().legend()

  sb.despine(left=True, bottom=False)
  pl.gca().xaxis.grid(False)
  pl.tight_layout()
  
  pl.show()


if __name__ == "__main__":
  parser = argparse.ArgumentParser(
    prog="jobs_sched_slots.py",
    description="Plots jobs scheduled slot [arrival time, arrival time + exec time], assuming execution on a single CPU and times is nanoseconds."
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