import pandas as pd
import numpy as np
import statistics

df = pd.read_csv("project1/ps.csv",delimiter=';',usecols=[i for i in range(27)])
df.replace(to_replace=np.nan,value=0,inplace=True)
df.drop_duplicates(subset="ID",inplace=True)

q1,q2,q3 = statistics.quantiles(df["Income"])
low_outlier = q1 - (1.5*(q3-q1))
upper_outlier = q3 + (1.5*(q3-q1))
print(low_outlier,upper_outlier)

q1,q2,q3 = statistics.quantiles(df["Year_Birth"])
low_outlier = q1 - (1.5*(q3-q1))
upper_outlier = q3 + (1.5*(q3-q1))
print(low_outlier,upper_outlier)

df.to_csv("ps2.csv",index=False,mode="w")